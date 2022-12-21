# December 20th, 2022

file = open(f"{__file__}/../input.txt", "r")

m = dict()
r = []
for line in file:
	line = line.strip().split(": ")
	name = line[0]
	args = line[1].split(" ")
	if name == "humn": continue
	if name == "root": args[1] = "="
	if len(args) == 1:
		m[name] = (0, int(args[0]))
	else:
		r.append((name, args))
m["humn"] = (1, 0)

while len(r):
	c = list(r)
	for name, args in c:
		if args[0] in m and args[2] in m:
			a1, a2 = m[args[0]]
			b1, b2 = m[args[2]]
			if args[1] == "+":
				m[name] = (a1 + b1, a2 + b2)
			elif args[1] == "-":
				m[name] = (a1 - b1, a2 - b2)
			elif args[1] == "*":
				m[name] = (a1 * b2 + b1 * a2, a2 * b2) # a1 * b1 = 0
			elif args[1] == "/":
				m[name] = (a1 / b2, a2 / b2) # b1 = 0
			else:
				if a1 == 0:
					print(int((a2 - b2) / b1))
				else:
					print(int((b2 - a2) / a1))
			r.remove((name, args))
