# December 20th, 2022

file = open(f"{__file__}/../input.txt", "r")

m = dict()
r = []
for line in file:
	line = line.strip().split(": ")
	name = line[0]
	args = line[1].split(" ")
	if len(args) == 1:
		m[name] = int(args[0])
	else:
		r.append((name, args))

while len(r):
	c = list(r)
	for name, args in c:
		if args[0] in m and args[2] in m:
			if args[1] == "+":
				m[name] = m[args[0]] + m[args[2]]
			elif args[1] == "-":
				m[name] = m[args[0]] - m[args[2]]
			elif args[1] == "*":
				m[name] = m[args[0]] * m[args[2]]
			else:
				m[name] = m[args[0]] / m[args[2]]
			r.remove((name, args))
print(int(m["root"]))
