---
course: cs170
cluster: DFS的区间结构
date: 2026-09-23
concepts: [边分类, edge classification, tree/forward/back/cross, DFS pre/post 引理, 有向图, directed graph, DFS树, DFS tree]
hook: 自己提的引理漏了 back edge 这一支——四类边分类本质是同一条不等式 pre(v)<post(u) 的四种展开，一步证明就够，不用分 tree/forward/back/cross 讨论
refs:
  - slides/lec-6_full.pdf   # Classifying edges using pre & post #'s
---

## 触发

推完 cross edge 为什么必须"v 先于 u 整个结束"（不能反过来）之后，觉得这套
讨论摸到了有向图很本质的结构，想自己总结一条引理。

## 卡点 / 误解

**我提的版本**："如果 u→v，那么要么 u 的括号包含 v 的括号，要么 v 的括号在
u 的括号之前。"

**这个版本漏了一种情况**——`u→v` 时，还有第三种可能：**v 的括号包含 u 的
括号**，也就是 **back edge**。最经典的例子：环 `A→B→C→A`，边 `C→A` 里
A 是 C 的祖先，`[pre(A),post(A)]` 整个包住 `[pre(C),post(C)]`——这正是"v
包含 u"，不属于我原来写的两种情况里的任何一种。完整列出来，给定
`u→v`，实际剩下三种合法情况：`u 包含 v`（tree/forward）、`v 包含 u`（back）、
`v 在 u 之前`（cross）；被排除掉的只有一种——`u 在 v 之前`。

## 关键 insight

把三种合法情况和一种被排除的情况一起翻译成 pre/post 的大小关系，会发现
前三种全都满足同一个不等式，只有被排除的那种违反它：

**引理**：若 `(u,v)∈E`（有向边），则恒有 `pre(v) < post(u)`。

这条比我原来的版本更强也更干净——**不用分三种情况讨论，一步就能证完**：
`explore(u)` 的循环一定会在 `post(u)` 之前扫到 `(u,v)` 这条边。扫到的那一刻，
不管 v 当时是什么状态：
- 未访问 → 立刻递归探进去，`pre(v)` 就在这一刻被设置，显然 `<post(u)`
- 已访问 → 说明 `pre(v)` 在更早的时刻已经设过了，自然更加 `<post(u)`

两种情况殊途同归，不用分 tree/back/cross 讨论就把它们全罩住了；反向也对——
违反这个不等式（`pre(v)>post(u)`）恰好精确对应那个被证明不可能发生的情况。
这条引理是整个"用 pre/post 分类边"体系背后真正的骨架：那张四选三的表，
本质上就是这一条不等式的四种展开方式，不是四条互相独立的规则。

无向图是这条引理的一个更极端的特例：见 [[undirected-has-no-cross]]，
无向边能双向走，直接把"cross"这个选项也堵死了，不只是排除"u 先于 v"。

## 遗留问题

（无）
