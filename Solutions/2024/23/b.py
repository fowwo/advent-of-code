# December 22nd, 2024

d = dict()

file = open(f"{__file__}/../input.txt", "r")
for line in file:
	a, b = line.strip().split("-")
	if a not in d: d[a] = set()
	if b not in d: d[b] = set()
	d[a].add(b)
	d[b].add(a)
file.close()

m = list(d.keys())
def f(i, s, r):
	if i == len(m): return s
	v = f(i + 1, s, r)
	if m[i] in r:
		v = max(v, f(i + 1, s | { m[i] }, r & d[m[i]]), key = len)
	return v

print(",".join(sorted(f(0, set(), set(m)))))
