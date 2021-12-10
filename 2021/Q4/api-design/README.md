## UI

page1:

```
身分證號 [   ]
健保卡號 [   ]
[查詢]
```

page2:

```
身分證號 [---]
健保卡號 [---]
[X] AZ
[X] BNT
[ ] MVC
[ ] MODERNA
[預約]
```

## Table/Entity

```
PERSON_INFO {
	身分證號,
	健保卡號,
	PhoneNo,

	List<VACCINE_APPOINTMENT> {
		VACCINE_ID;
		int 全民排隊號;
	}

	List<BETRAY> {
		LocalDateTime deleteTime;
		VACCINE_ID;
	}
}

VACCINE {
	NAME;
	REMARK;
	...
}
```

## CRUD API Sytle:

- 查詢單筆

```
find PERSON_INFO by 身, 健
```

- 新增或更新預約

```
find PERSON_INFO
if not exist > insert PERSON_INFO
else > 
	update PERSON_INFO fields
	if UI 有 && db 沒有 => insert;
	if UI 沒有 && db 有 ==> {
		delete VACCINE_APPOINTMENT;
		insert BETRAY;
	}
	both 有 => do nothing;
```

- 查詢疫苗

```
find all form VACCINE
```

## 命令 API Style

- 註冊 PERSON_INFO

```
insert PERSON_INFO
```

- 修改 PERSON_INFO

```
{
	phoneNo
}

if 人 not exist > throw Exception
update PERSON_INFO fields
```
- 加選預約

```
if 人 not exist > throw Exception
if UI 有 && db 沒有 => insert;
```

- 退選預約

```
if 人 not exist > throw Exception
if UI 沒有 && db 有 ==> {
	delete VACCINE_APPOINTMENT;
	insert BETRAY;
}
```

- 更換預約

```
begin
	加選預約
	退選預約
end
```

