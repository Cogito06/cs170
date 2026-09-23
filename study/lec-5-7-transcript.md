# lec-5 / 6 / 7 转录

手写扫描件转成的可读文字。PDF 有密码保护，用 `pdftoppm` 渲染成图片逐页读的。
跳过了课程事务页和纯装饰图（老师自我介绍、Kosaraju/Sharir 照片）。

原件：`slides/lec-5_full.pdf`（16 页）、`lec-6_full.pdf`（12 页）、`lec-7_full.pdf`（17 页）

---

# Lecture 5：图基础 + DFS（无向图连通性）

## 基本定义

```
G = (V, E)
无向图：边无方向
有向图：边有方向，(u,v)∈E 表示 u→v

n = |V|，m = |E|，满足 m ≤ n²
deg(u) = 无向图里 u 的度数
有向图区分 in-degree(v) 和 out-degree(v)
```

三个引子：Facebook 好友图（n=29.1 亿，m=5000 亿，无向）；Google Maps 最短路径；
迷宫求解（入口 u、出口 v，问是否连通——「连通性」最直观的版本）。

## 图怎么存

1. **邻接矩阵**：`A[i][j] = 1 if (i,j)∈E else 0`，n×n
2. **邻接表**：每个顶点挂一条无序链表，例：1→(2,3)，2→(3)，3→(∅)

| | 邻接矩阵 | 邻接表 |
|---|---|---|
| 空间 | O(n²) | O(n+m) |
| 判断 (u,v)∈E | O(1) | O(deg(u)) |
| 枚举 u 的邻居 | O(n) | O(deg(u)) |

Facebook 图若用邻接矩阵：n² ≈ 8.5×10¹⁸ ≈ 100 万 TB。求某人的好友列表，
邻接表 O(deg(u))≈338 步，邻接矩阵要扫 29.1 亿步——**这就是为什么现实系统必须用邻接表**。

## explore(G,u)

```
explore(G, u):
    visited[u] = true
    for v such that (u,v)∈E:
        if visited[v] == false:
            explore(G, v)
```

**正确性**：恰好访问所有与 u 有路径相连的顶点。

- 方向一（v 被访问 ⟹ 有路径）：走的就是递归调用链上的边
- 方向二（有路径 ⟹ v 被访问）：反证。设路径 u=u₀→…→uₖ=v 但 v 没被访问，
  取路径上最后一个被访问的顶点 u_{k-1}，则 explore 跑到它时理应递归调用
  explore(G, uₖ)——矛盾

## dfs(G) 与连通分量

```
dfs(G):
    boolean array visited[n]    (全 false)
    count = 1
    int array ccnum[n]
    for v ∈ V:
        if visited[v] == false:
            explore(G, v)
            count = count + 1

explore(G, u):            # 加一行 ccnum
    visited[u] = true
    ccnum[u] = count
    for v such that (u,v)∈E:
        if visited[v] == false:
            explore(G, v)
```

## 运行时间

每个顶点只调用一次 explore：`visited[u]=true` 是 O(1)，枚举邻居是 O(deg(u))

```
total = Σ_{u∈V} O(1+deg(u)) = O(n+m)
```

## pre / post 时间戳

```
explore(G, u):
    visited[u] = true
    pre[u] = clock;  clock = clock+1
    for v such that (u,v)∈E:
        if visited[v] == false:
            explore(G, v)
    post[u] = clock; clock = clock+1
```

例子给出的区间嵌套：A[1,10] → B[2,7] → D[3,6] → E[4,5]，A → C[8,9]。
**每个顶点的区间恰好包住它子树里所有顶点的区间**——这个嵌套结构是 lec-6 分类边的关键。

---

# Lecture 6：有向图——边分类、拓扑排序、SCC

## 用 pre/post 分类边

设 (u,v)∈E，看两个区间的相对位置：

```
[u_pre [v_pre v_post] u_post]   →  tree 边 或 forward 边
[v_pre v_post] [u_pre u_post]   →  cross 边
[v_pre [u_pre u_post] v_post]   →  back 边
（另外两种相对位置对 DFS 树不可能出现）
```

**关键事实**：`(u,v) 是 back edge ⟺ post(u) < post(v)`

tree/forward 边指向还没关闭的子孙——子孙先关闭，所以 post(子孙)<post(u)；
back 边指向祖先——祖先的区间整个包住 u，所以 post(祖先)>post(u)；
cross 边指向已经完全跑完、和 u 毫无祖先关系的顶点。

## 应用 1：环检测

引子：书的索引互相 "see also" 形成环（args→arguments、infinite loop→loop,infinite→infinite loop）。

**命题**：对 G 跑 DFS，`G 是 DAG ⟺ 没有 back edge`

1. 有 back edge ⟹ 不是 DAG（back edge u→v 加上树上 v 到 u 的路径构成环）
2. 不是 DAG ⟹ 有 back edge（沿环走，pre 最小的那个顶点，从它连到环上前一个顶点的边必是 back edge）

算法：跑 DFS，没有 back edge 就输出 "DAG"。判断只需查 `post(u) > post(v)`。

## 应用 2：拓扑排序

引子：电影宇宙依赖图（先看 Iron Man 才能看 Avengers）。

**命题**：对 DAG 跑 DFS，则对所有 (u,v)∈E 都有 `post(u) > post(v)`
证明：DAG ⟹ 无 back edge ⟹ 所有边满足该不等式
（tree/forward/cross 三类本来就满足，back 边是唯一反例，已被排除）

**算法**：跑 DFS，按 post 从大到小排序顶点。

## 应用 3：SCC

**定义**：`u,v 强连通 ⟺ u 到 v 有路径 且 v 到 u 也有路径`

**命题**：这是等价关系（自反、对称、传递）——据此划分出若干 SCC。
例子图划出 {A}、{B,E,F,C}、{D}、{G,H,I,J,K,L} 四个。

**元图**：每个 SCC 缩成一个点，SCC 间的边按原方向连。
例子：source={A}，两个 sink：{D} 和 {G,H,I,J,K,L}。

**命题**：元图一定是 DAG
（反证：元图若有环，那些 SCC 能互相到达，本该合并成更大的 SCC）

## 计算 SCC（Kosaraju 1978 / Sharir 1981）

核心想法：假设有个「魔法算法」能给出一个落在 **sink SCC** 里的顶点 u。
从 u 开始 explore，恰好访问 u 所在 SCC 的全部顶点——因为 sink SCC
没有指向外部的边，走不出去；而 SCC 内部互相强连通，所以走得到所有队友。

```
从一个 sink SCC 里的点开始 explore，探到的整个集合就是这个 SCC → 删掉
在剩下的图里重复：找下一个 sink SCC，explore，删掉
……
```

**「魔法算法：DFS！（加一点变化）」**——lec-6 到此结束，坑留到 lec-7。

---

# Lecture 7：补完 SCC + 最短路径入门

## finish(C) 与第一个 Claim

对每个 SCC C，定义 `finish(C) = C 里所有顶点中最大的 post 值`。

**Claim**：若元图里有边 C→C'，则 `finish(C) > finish(C')`

分两种情况（哪个 SCC 先被 DFS 碰到）：

- **先访问 C**：DFS 先探完整个 C，再沿 C→C' 探进 C'，所以 C 里必有一点的 post > finish(C')
- **先访问 C'**：DAG 性质保证不存在 C'→C 的边，所以只有等 C' 完全探完之后
  才可能从别的路径进到 C。因此 C 里所有点的 post 都 > finish(C')

**Claim 2**：全局 post 最高的顶点一定落在 **source SCC** 里。
反证：假设不在，沿指向它的边一路回溯，每次都能找到 finish 更大的 SCC
（图上标注 "even bigger!"），只能在 source 处停下——矛盾。

## 反转图补上最后一步

引入 `G^R`：把所有边方向倒过来。

**Claim**：G 和 G^R 拥有相同的 SCC。在元图里边全部反向，**source 和 sink 互换**。

于是：先在 G^R 上跑 DFS 算出 post_R。post_R 最高的顶点 u——
由 Claim 2（用在 G^R 上）落在 G^R 的 source SCC 里，
而 G^R 的 source SCC = **G 的 sink SCC**。这正是魔法算法要的东西。

## 完整的 Kosaraju

```
Find_SCCs(G):
    for all u: visited[u] = false
    在 G^R 上跑一遍 DFS，算出 post_R 值
    count = 1
    for u ∈ V （按 post_R 从大到小的顺序）:
        if visited[u] == false:
            explore(G, u)        ← 这一步在原图 G 上做
            count = count + 1
```

**关键点**：两次 DFS——第一次在 G^R 上只为拿 post_R 排序，
第二次真正的 explore 在原图 G 上做，但访问顺序按 post_R 从大到小。
每次 explore 恰好圈出一个完整 SCC（先圈 sink，逐步往 source 剥）。

---

## Paths in Graphs（新主题）

**SSSP**：输入图 G 和源点 s，输出所有 u 的 `d(s,u)`。

| 情形 | 算法 |
|---|---|
| 无权图（边长 1） | BFS |
| 正权图 | Dijkstra |
| 任意权重（含负权） | Bellman-Ford |

引子：Kevin Bacon number、Erdős number。

### 无权图 → BFS

直觉：以 s 为圆心画同心圆，一层层往外扩。对比 DFS 树：
**DFS 会一条路径深挖到底，给出的树不是按距离分层的**——
这正是为什么无权图最短路径需要 BFS。

```
bfs(G, s):
    dist[s] = 0
    for all u≠s: dist[u] = ∞
    Q = {s}
    while Q is not empty:
        u = dequeue(Q)
        for all v s.t. (u,v)∈E:
            if dist[v] == ∞:
                enqueue(Q, v)
                dist[v] = dist[u] + 1
```

运行时间 O(n+m)。**"DFS is just BFS with a stack"**——把队列换成栈，
框架直接变成 DFS，两者只差一个数据结构。

### 正权图 → Dijkstra

核心：不是一层层扩，而是**按最终最短距离从小到大逐个确定顶点**。

K = 已确定的集合，U = V \ K。

**关键观察**：s 到 v_{i+1} 的最短路径上，除终点外其余所有点必须全在 K 里——
否则路径中途那个 U 中的点会比 v_{i+1} 更早被确定，矛盾。

```
dist[v] = d(s,v)                          if v∈K
        = min_{u∈K} { dist[u] + l(u,v) }  if v∈U
```

每次把 v_{i+1} 收进 K 后更新邻居：`dist[w] = min{ dist[w], dist[v_{i+1}] + l(v_{i+1},w) }`

```
dijkstra(G, l, s):
    dist[s] = 0;  for all u≠s: dist[u] = ∞
    U = V    (全部插入优先队列 —— n 次 Insert)
    while U is not empty:
        u = 取出 dist 最小的点并删除    (DeleteMin —— n 次)
        for each v s.t. (u,v)∈E:
            dist[v] = min{ dist[v], dist[u]+l(u,v) }
            DecreaseKey(v, dist[v])       (最多 m 次)
```

| 优先队列实现 | Insert | DeleteMin | DecreaseKey | 总计 |
|---|---|---|---|---|
| 数组 | O(1) | O(n) | O(1) | O(n²) |
| 二叉堆 | O(log n) | O(log n) | O(log n) | O((n+m)log n) |
| 斐波那契堆 | O(1) | O(log n) | O(1) | O(n log n + m) |

旁注：Mikkel Thorup 2004 给出 O(n log log n + m)，写在角落，大概率是课堂彩蛋。

---

Bellman-Ford 只列了名字没展开，应该是下一讲。
