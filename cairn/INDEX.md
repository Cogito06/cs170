<!-- cairn:v1 -->
# Cairn Index

## 整数乘法能多快（3 条）
- [base-tradeoff-asymmetry] 进制/base/Four Russians/字长/时空权衡 — 「大 base = 空间换时间」方向对但兑换率极差：时间按 w² 变好、空间按 4^w 变坏；且 CPU 的 64 位乘法不是查表而是 O(w²) 门的电路 | open:1 | 09-15
- [change-model-vs-change-algorithm] 计算模型/computational model/Karatsuba/FFT/整数乘法 — 进制/字长/查表属于「改模型」只赚 log，Karatsuba/FFT 属于「改算法」赚指数——n log n 猜错了竖式，却猜中了整数乘法的真实上界 | open:0 | 09-15
- [halve-is-not-log] 俄罗斯农夫乘法/Russian Peasant Algorithm/位数/bit complexity/竖式乘法 — 「每轮减半→log n 层」是归并排序的错误 pattern-match：减半的是值不是问题规模，值减半只砍掉 1 个 bit，所以 n 轮不是 log n 轮 | open:0 | 09-15

## 复杂度从哪来（3 条）
- [karatsuba-cant-reach-balance] 整数乘法/integer multiplication/点值表示/point-value representation/Karatsuba — Karatsuba 把 a 从 4 掰到 3 但够不到平衡点 a=b^d=2，所以永远叶重；FFT 落在边界不是因为切得巧，是因为它换了表示法 | open:1 | 09-15
- [log-n-is-the-depth] n log n/主定理/Master Theorem/临界指数/critical exponent — 主定理的 log n 是递归树层数、不是算出来的函数——它只在 r=1 时现身，因为几何级数退化成计数；和 ∫x^p dx 在 p=−1 退化成 ln 是同一个 (X^c−1)/c → ln X | open:1 | 09-15
- [per-node-not-per-level] 主定理/Master Theorem/递归树/recursion tree/几何级数 — f(n)=n^d 是每节点代价不是每层代价：递归项给 a^k、求和项给 b^(−kd)，相除才得到天平 r=a/b^d；「d vs log_b a」就是「r vs 1」的换写 | open:0 | 09-15
