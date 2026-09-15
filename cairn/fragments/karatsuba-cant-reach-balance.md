---
course: cs170
cluster: 复杂度从哪来
date: 2026-09-15
concepts: [整数乘法, integer multiplication, 点值表示, point-value representation, Karatsuba, FFT, 主定理, Master Theorem, 分治, divide and conquer]
hook: Karatsuba 把 a 从 4 掰到 3 但够不到平衡点 a=b^d=2，所以永远叶重；FFT 落在边界不是因为切得巧，是因为它换了表示法
refs:
  - slides/lec-2_full.pdf
  - slides/lec-1_full.pdf
---

## 触发

学完主定理，拿它回头看上一轮的整数乘法讨论
（[[change-model-vs-change-algorithm]]），把 Karatsuba 和 FFT 的 (a,b,d) 摆进天平里。

## 卡点 / 误解

**我原以为** Karatsuba 和 FFT 是「同一条路上走得更远」，FFT 只是切得更聪明。

不是。**三个参数里能调的只有一个**——`b=2`（对半切）和 `d=1`（合并时那些加减法）
被问题本身钉死了，唯一的杠杆是 `a`：

```
                      a   b   d   log_b a          结果
朴素分治乘法          4   2   1   2        叶重   n²
Karatsuba             3   2   1   1.585    叶重   n^1.585
──── 平衡点 a = b^d = 2 ────────────────────────────────  ← 够不到
FFT                   2   2   1   1        边界   n log n
```

Karatsuba 干的全部事情就是**把 a 从 4 掰到 3**，天平往左推了一格——但还在叶重区。
要落到平衡点需要 `a = b^d = 2`，也就是「用两次半规模乘法算出一次全规模乘法」，
对整数乘法做不到。**所以顺着 Karatsuba 这条路走，永远走不出 n log n。**

## 关键 insight

FFT 的 `(2,2,1)` 恰好落在边界上，所以它给 `n log n`。但它拿到 `a=2`
**不是因为切得更聪明——它根本没在切同一个问题**，它换了表示法
（系数 → 点值，于是卷积变成逐点乘）。

这给 [[change-model-vs-change-algorithm]] 的二分补了一层：
**换表示法不是第三条路，是「改算法」里最激进的一种**——不改怎么分，改问题的坐标系。

顺带一个观察：主定理边界上那些算法，没有一个是碰巧站在那儿的。

```
二分查找   (1, 2, 0)   log_2 1 = 0 = d
归并排序   (2, 2, 1)   log_2 2 = 1 = d
FFT        (2, 2, 1)   log_2 2 = 1 = d
```

**`n log n` 算法是被设计成正好站在天平中点的**，而那个 log 就是它们的层数——
见 [[log-n-is-the-depth]]。反过来说，你想要 `n log n`，就得先把算法凑到
`log_b a = d` 上，见 [[per-node-not-per-level]]。

## 遗留问题

- [ ] a 只能取整数，log_b a 却连续扫过所有实数——「刚好落在边界」是人为设计的巧合，还是有更深的原因？
