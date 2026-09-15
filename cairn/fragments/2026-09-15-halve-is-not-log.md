---
id: 2026-09-15-halve-is-not-log
course: cs170
concepts: [俄罗斯农夫乘法, Russian Peasant Algorithm, 埃及乘法, Egyptian multiplication, 位数, bit complexity, 竖式乘法, schoolbook multiplication]
hook: 「每轮减半→log n 层」是归并排序的错误 pattern-match：减半的是值不是问题规模，值减半只砍掉 1 个 bit，所以 n 轮不是 log n 轮
refs:
  - slides/lec-1_full.pdf   # "Can we do better?" 那页，右下角 At home 那两问
  - book/chap1.pdf
status: resolved
---

## 触发

看 lec-1 幻灯片 "Can we do better?" 里的俄罗斯农夫 / 埃及乘法（27 × 19，
一列不断 `⌊x/2⌋`、一列不断 ×2，划掉第一列是偶数的行，剩下的加起来 = 513），
问它的复杂度，自己先猜了个 `n log₂ n`。

## 卡点 / 误解

**我原以为**：每轮把第一列减半 → 像归并排序一样有 log n 层 → 每层 O(n) → `n log n`。

**实际不是**，两个来源：

**(a) 归并排序的错误 pattern-match。** 归并排序减半的是**问题规模**（n 个元素
变成 2 个 n/2 的子问题）；这里减半的是**一个数的值**。值减半 = 二进制右移一位
= 位数只少 1。同样是「halve」，一个把规模除以 2，一个把规模减 1。

```
27 → 13 → 6 → 3 → 1 → 0           5 轮，不是 log 5 轮
11011 → 1101 → 110 → 11 → 1 → 0   每次掉一个 bit
```

所以轮数 = x 的**位数** = n 轮。

**(b) n 混用了。** 幻灯片上面那句 "It takes at least n steps to just read the
numbers" 已经定死了 n = **位数**，不是数值。如果心里的 n 是数值 N，那轮数确实
是 log N——但那时每轮的加法操作数有 Θ(log N) 位，还是 log²N = 位数²。两条路
殊途同归，因为本来就是同一个算法。

**「halve」是个陷阱词。** 以后看到它先问一句：减半的是规模还是值？

## 关键 insight

**删掉偶数行 = 挑出 x 的二进制 1 位。** 对着例子，从下往上读左边那列奇偶性
就是 27 的二进制 `11011`：

```
 1   27   19  ✓      ← bit 0
 1   13   38  ✓      ← bit 1
 0    6   76  ✗      ← bit 2
 1    3  152  ✓      ← bit 3
 1    1  304  ✓      ← bit 4
                513

19 × 27 = 19 × (16 + 8 + 2 + 1) = 304 + 152 + 38 + 19 = 513
```

所以这根本不是什么神秘的埃及技巧，**它就是二进制竖式**。

这一个观察同时答掉幻灯片 At home 的两问：
- **正确性** = 上面那个二进制展开式
- **复杂度** = n 个 bit → 最多 n 行 → 每行一次加法

关键细节：第二列**每轮长一位**（第 i 行是 `y·2^i`，有 n+i 位），不能把每次加法
当 O(1)：

```
  n-1
  Σ  O(n + i)  =  O( n² + n(n-1)/2 )  =  O(n²)
  i=0
```

下界取 `x = 2ⁿ−1`（全 1），一行都删不掉，后 n/2 行每次加法都 Ω(n) → Ω(n²)。

所以 `T(n) = Θ(n²)`——**和小学竖式完全一样，没有 better**。标题 "Can we do
better?" 在这里的答案是「这个算法不行」，真正的突破要等分治。
见 [[2026-09-15-change-model-vs-change-algorithm]]。

## 遗留问题

（无）
