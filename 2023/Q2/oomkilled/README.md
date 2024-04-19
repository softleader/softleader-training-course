# 為啥我部署到 Kubernetes 的程式被 OOMKilled 了!?

- [錄影檔](https://drive.google.com/file/d/1Mg8ieX40bZN-BqU4dvC2YwS6l4fCVmeY/view?usp=drive_link), 建議 1.25 倍速看
- [影片中畫的圖的素材](./material.drawio)
- [影片中的範例程式](./oom.zip)

## 影片中畫的圖

### Arch

![](./material-arch.drawio.svg)

### Overcommit
Linux 中的 Overcommit 是一種記憶體資源管理策略,允許系統在實際可用的實體記憶體不足時,仍然可以分配更多虛擬記憶體給程序使用。這種策略的出發點是基於大多數程序在執行期間實際使用到的記憶體往往遠小於其地址空間大小,因此即使分配了大量虛擬記憶體,實際上系統也不會遇到記憶體不足的問題。

Overcommit 有以下三種模式:

1. **Heuristic Overcommit**
   這是 Linux 預設的模式,內核會根據經驗值對可用記憶體和swap空間做出評估,允許在合理範圍內進行過度分配。如果估計後續可能導致記憶體不足,內核將拒絕分配請求。

2. **Always Overcommit**
   如果設置了該模式,內核將允許無限制地分配虛擬記憶體,不管實際的可用記憶體和swap空間是否足夠。這種模式的風險在於如果所有程序同時使用分配到的記憶體,可能會導致系統記憶體耗盡而失去響應。

3. **Never Overcommit**
   這是最保守的模式,內核僅允許分配不超過可用物理記憶體加上swap空間的虛擬記憶體,以避免記憶體不足的風險。但這種模式可能會導致低效的記憶體利用率。

舉個例子,假設系統有 4GB 的物理記憶體和 4GB 的 swap 空間,如果採用 Always Overcommit 模式,那麼內核可以不受限制地分配虛擬記憶體給程序使用。比如某個程序請求 10GB 的虛擬記憶體,內核將允許這種分配,即便實際上只有 8GB 的可用記憶體空間。這種過度分配可能會導致記憶體不足的風險,從而觸發 Linux 的 OOM Killer,強制殺死某些程序以保證系統的穩定運行。

### OOM Killer
OOM Killer (Out Of Memory Killer) 是 Linux 內核中用於處理記憶體不足情況的一種機制。當系統記憶體耗盡時,OOM Killer 會根據一定的評分策略選擇並強制終止一個或多個正在運行的程序,以釋放記憶體並防止整個系統因記憶體不足而癱瘓。

OOM Killer 的評分策略主要考慮以下幾個因素:

1. **進程的可用性 (Oom_score)**
   每個進程都有一個 oom_score 值,表示其可殺掉的程度。分數越高,越有可能被殺掉。例如系統進程的分數較低。

2. **記憶體使用量**
   正在使用大量記憶體的進程會獲得較高的分數。Linux 內核根據以下公式計算每個進程的最終 OOM_score:
   ```
   OOM_score = oom_score_adj * oom_score_adj_max + vm.oom_score_adj
   ```
  * `oom_score_adj`: 進程的 oom_score_adj 值,範圍 -1000 到 1000
  * `oom_score_adj_max`: 系統定義的最大 oom_score_adj 值,通常為 1000
  * `vm.oom_score_adj`: 根據記憶體使用量等系統參數計算出的基礎分數

3. **是否允許 OOM**
   有些進程設置了 oom_adj 值為 -17 (不允許被 OOM Killer 殺掉)。

4. **CPU 時間**
   最近消耗大量 CPU 時間的進程會獲得較高的分數。

5. **具有 root 權限**
   有 root 權限的進程會獲得較低的分數。

6. 常用指令
  * 查看單個進程的 oom_score_adj 值
     ```bash
     cat /proc/[pid]/oom_score_adj
     ```
  * 查看單個進程的 oom_score 值
     ```bash
     cat /proc/[pid]/oom_score
     ```
  * 查看所有進程的 oom_score_adj 和 oom_score
     ```bash
     ps -eo pid,comm,oom_score,oom_score_adj
     ```
  * 使用 oomkill_cgroup 監控 OOM 事件
     ```bash
     tail -f /path/to/oomkill_cgroup
     ```

### cgroup

![](./material-cgroup.drawio.svg)

cgroups (控制組groups) 是 Linux 內核提供的一種將進程劃分為不同組群、實現資源限制和優先級控制的機制。它允許系統管理員對一組進程及其子進程的資源使用(如CPU、內存、磁碟I/O等)進行分組、監控和限制。

cgroups的主要作用包括:

1. **資源限制**:可對組內進程的CPU使用、內存使用、磁盤I/O等資源設置限額。

2. **優先級分配**:可為不同資源類型設置優先級,實現組間的資源分配。

3. **審計**:可記錄組內進程的資源使用統計數據,方便資源分配決策。

4. **控制**:可暫停/恢復/kill組內的一個或多個進程。

5. **isolate**:將一組進程隔離在指定單獨的環境中運行。

每個cgroup都對應一個相對於文件系統的目錄,該目錄路徑反映了cgroups的層次結構。下面是一個cgroups的例子:

假設有一個Web服務器環境,包含以下進程:

- nginx主進程
- 10個nginx worker進程
- MySQL進程
- 其他輔助進程

我們可以通過以下步驟使用cgroups對這些進程進行隔離和限制:

1. 創建一個cgroup目錄:
   ```
   mkdir /sys/fs/cgroup/cpu/webserver
   ```

2. 設置webserver cgroup的CPU限制,比如最多使用50%的CPU資源:
   ```
   echo 50000 > /sys/fs/cgroup/cpu/webserver/cpu.cfs_quota_us
   ```

3. 將nginx主進程加入webserver cgroup:
   ```
   echo ${nginx_main_pid} > /sys/fs/cgroup/cpu/webserver/tasks
   ```
   這樣nginx的所有子worker進程也會自動加入。

4. 將MySQL進程加入webserver cgroup:
   ```
   echo ${mysql_pid} > /sys/fs/cgroup/cpu/webserver/tasks
   ```

5. 這樣無論nginx和MySQL進程消耗多少CPU,它們的總CPU使用率就被限制在50%以內了。

6. 在 Docker 中,我們可以透過 --cpu-shares、--cpu-quota、--cpuset-cpus、--memory 等參數來設置容器的 cgroups 限制。以下是一個範例 Dockerfile,示範如何限制容器的 CPU 和記憶體資源:

  ```Dockerfile
  FROM openjdk:8-jre-slim

# 安裝一些必要的套件
RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/*

# 複製應用程式至容器內
COPY ./target/myapp.jar /opt/app/

# 設置容器的資源限制
# CPU shares 設為 512 (預設為 1024)
# 等同於獲取 50% 的 CPU 週期 
CPU_SHARES=512

# 設置 CPU 配額,限制容器最多使用單個 CPU 核心的 50%
# 此值需根據容器中的進程數量調整
CPU_PERIOD=100000
CPU_QUOTA=50000  

# 限制記憶體使用量為 2GB
MEM_LIMIT=2048m

WORKDIR /opt/app

# 另外,在啟動 Java 應用時,傳遞了一些 JVM 參數:
# -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap: 告知 JVM 自動設置最大可用堆大小為 cgroup 限制的記憶體值。
# -XX:MaxRAMFraction=1: JVM 最多可使用主機可用記憶體的 100%。
# -Xmx${MEM_LIMIT} -Xms${MEM_LIMIT}: 設置 JVM 初始和最大堆大小為 Docker 主機分配的記憶體限制。
CMD java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap \
         -XX:MaxRAMFraction=1 \
         -Dcom.sun.management.jmxremote \
         -Xmx${MEM_LIMIT} \
         -Xms${MEM_LIMIT} \
         -cp myapp.jar com.mycompany.app.Main
  ```

除了CPU限制,cgroups還可以對內存、磁碟I/O、網絡等資源進行細緻的配置。通過合理劃分cgroups,可以避免某些進程壟斷資源,影響其他應用的正常運行,提高整體系統的穩定性。

### node

![](./material-node.drawio.svg)

### flow

![](./material-flow.drawio.svg)

## References

- [Reserve Compute Resources for System Daemons](https://kubernetes.io/docs/tasks/administer-cluster/reserve-compute-resources/#eviction-thresholds)
- [OOM Score configuration at the Nodes](https://github.com/kubernetes/design-proposals-archive/blob/main/node/resource-qos.md)
- [Flows leading to out-of-memory situations](https://mihai-albert.com/2022/02/13/out-of-memory-oom-in-kubernetes-part-4-pod-evictions-oom-scenarios-and-flows-leading-to-them/#flows-leading-to-out-of-memory-situations)