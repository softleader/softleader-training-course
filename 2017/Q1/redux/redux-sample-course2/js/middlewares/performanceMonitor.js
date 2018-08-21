/**
 * 監控每個 action 的 performance
 */
const performanceMonitor = store => next => action => {

  let begin = new Date().getTime();
  let nextAction = next(action);
  let costTime = new Date().getTime() - begin;

  console.group(" performanceMonitor " + action.type);
  console.log('cost time: ', costTime + ' ms');
  console.groupEnd(action.type);

  return nextAction;
}

export default performanceMonitor;