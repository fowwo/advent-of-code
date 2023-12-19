# December 19th, 2023

file = open(f"{__file__}/../input.txt", "r")
plans = [ line.strip().split()[-1][2:-1] for line in file ]
plans = [ (int(plan[-1]), int(plan[:-1], base = 16)) for plan in plans ]

r = c = 0
rm = set()
cm = set()
for d, v in plans:
	r, c = [ (r, c + v), (r + v, c), (r, c - v), (r - v, c) ][d]
	if d & 1: cm.add(c)
	else: rm.add(r)
rm = sorted(rm)
cm = sorted(cm)

w = set()
i, j = rm.index(0), cm.index(0)
for d, v in plans:
	r, c = [ (r, c + v), (r + v, c), (r, c - v), (r - v, c) ][d]
	oi, oj = [ (0, 1), (1, 0), (0, -1), (-1, 0) ][d]
	wi, wj = [ (-1, 0), (0, 0), (0, -1), (-1, -1) ][d] # CW
	w.add((i + wi, j + wj))
	while rm[(i := i + oi)] != r or cm[(j := j + oj)] != c:
		w.add((i + wi, j + wj))

s = 0
t = set()
m = { (rm.index(0), cm.index(0)) }
while m:
	n = set()
	for (r, c) in m:
		t.add((r, c))
		s += (rm[r + 1] - rm[r] + 1) * (cm[c + 1] - cm[c] + 1)
		v = [ False ] * 4
		for i, p in enumerate([ (r, c + 1), (r + 1, c), (r, c - 1), (r - 1, c) ]):
			if p not in w:
				if p in t:
					v[i] = True
					s -= (rm[r + 1] - rm[r] + 1) if r == p[0] else (cm[c + 1] - cm[c] + 1)
				else:
					n.add(p)
		s += sum(1 if v[i] and v[i - 1] else 0 for i in range(4))
	m = n
print(s)
