# December 18th, 2023

file = open(f"{__file__}/../input.txt", "r")

workflow = dict()
while (line := file.readline().strip()):
	name, rules = line.strip()[:-1].split('{')
	rules = rules.split(",")
	n = []
	for rule in rules[:-1]:
		i, m = rule.split(':')
		n.append((i[0], i[1], int(i[2:]), m))
	n.append((rules[-1]))
	workflow[name] = n

def f(n, p):
	if n == "R": return 0
	if n == "A":
		v = 1
		for x, y in p.values():
			v *= (y - x + 1)
		return v
	w = workflow[n]
	q = False
	s = 0
	for (v, c, t, m) in w[:-1]:
		l, r = p[v]
		if c == "<":
			if l < t:
				newDict = dict(p)
				newDict[v] = (l, min(r, t - 1))
				s += f(m, newDict)
				p[v] = (t, r)
			if r < t:
				q = True
				break
		else:
			if r > t:
				newDict = dict(p)
				newDict[v] = (max(l, t + 1), r)
				s += f(m, newDict)
				p[v] = (l, t)
			if l > t:
				q = True
				break
	if not q: s += f(w[-1], p)
	return s
print(f("in", dict(zip("xmas", [(1, 4000)] * 4))))
