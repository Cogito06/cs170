This is a repo for my learning cs170, the algorithm course of UCBerkeley.
Very glad to see you here!

--Cogito06, edited in 2026/9/13

---

## What is `cairn/`?

Not course material — it's where I keep the things I got *wrong* first.

Each fragment in `cairn/fragments/` records one misconception and how it broke:
what I believed, why it was wrong, and the connection that fixed it. Textbook
content stays in the textbook; these notes only point at it.

`cairn/INDEX.md` is generated from the fragments, never written by hand. Rebuild it with:

```bash
python ~/.claude/skills/cairn/scripts/rebuild_index.py cairn
```

Built with [Anamnesis](https://github.com/Cogito06/Anamnesis), a cross-session
memory layer for Claude Code — it re-injects these notes at the start of every
session so I don't re-learn the same mistake twice.
