## AOP基本概念

1. CGLIB, JDK sample
2. On the way in/our differents in order
3. call method inside class
4. ordering

## Propagation

- REQUIRED
- SUPPORTS
- REQUIRES_NEW
- NOT_SUPPORTED
- ...

## Stories

- REQUIRED from service a to b and commit
- b exception, but a try catch
- globalRollbackOnParticipationFailure
- b 改成 REQUIRED_NEW but order 2
- NOT_SUPPORTED 

