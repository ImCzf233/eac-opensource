## Ensemble Anti Cheat opensource

EAC开源计划，本计划是对Ho3在2020年进行的Open Hanabi计划进行致敬。

本源码为逆向获得，除了check方法逆向准确率高达99%，可直接编译。

### EAC是什么？

EAC是一款由Ensemble外挂开发团队为了绕过虎牙宇宙服务器而用脚写的一款垃圾客户端反作弊。大部分代码均为Ensemble外挂作者抄袭而来，代码质量甚至高于李宇轩的Reflect外挂。

同时Ensemble外挂开发者还拥有极强的圈钱能力，其反作弊售卖价格甚至高于大部分付费客户端反作弊，而质量仅价值十元甚至九元。虎牙宇宙服务器拥有者张先知甚至不知道这款垃圾反作弊早期内置后门，甚至早期版本的垃圾免杀会被卡巴斯基安全软件拦截。

### Ensemble是什么？

Ensemble是一款1.8.9垃圾Forge外挂。其包含大量抄袭内容，包括但不限于Novoline开源版本的HUD Inventory等。

在另一款垃圾外挂[Slowly](https://www.slowlycrazy.lol/)（为近期国内抄袭的一款垃圾1.12.2热注入客户端）抄袭了Ensemble外挂的一些抄袭的代码，于是Ensemble外挂开发者们突击Slowly外挂的QQ群组进行魔怔。

我无法评价抄袭Ensemble抄袭的代码就是错误的，Ensemble抄袭代码就是正确的这种观点，也许是Ensemble开发者平均学历不到初中和家庭教育缺失造成的。

### 如何编译Java代码

导入IntelliJ IDEA或Eclipse等IDE软件之后导入ForgeBin 1.8.9和Netty以及LaunchWrapper库，即可编译，编译后记得使用Recaf或者ForgeGradle Remapping为SRG命名。

### 如何编译C代码

导入MinHook libs和头文件即可编译。

代码前添加

```
#pragma comment(lib, "MinHooklibs路径")
```
