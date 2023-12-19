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

s = 0
while (line := file.readline().strip()[1:-1]):
	p = dict(zip("xmas", (int(x[2:]) for x in line.split(","))))
	n = "in"
	while n not in "AR":
		w = workflow[n]
		q = False
		for (v, c, t, m) in w[:-1]:
			if (c == "<" and p[v] < t) or (c == ">" and p[v] > t):
				n = m
				q = True
				break
		if not q: n = w[-1]
	if n == 'A': s += sum(p.values())
print(s)
