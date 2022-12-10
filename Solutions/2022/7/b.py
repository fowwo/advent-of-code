# December 10th, 2022

file = open(f"{__file__}/../input.txt", "r")

path = [ "/" ]
size = dict()
nest = dict()

line = file.readline().strip()
while line:
	args = line.split(" ")
	if args[1] == "cd":
		if args[2] == "/":
			path = [ "/" ]
		elif args[2] == "..":
			path.pop()
		else:
			path.append(args[2])
		line = file.readline().strip()
	else: # ls
		s = 0
		ns = set()
		line = file.readline().strip()
		while line and not line.startswith("$"):
			args = line.split(" ")
			if args[0] == "dir":
				ns.add(args[1])
			else:
				s += int(args[0])
			line = file.readline().strip()
		size["/".join(path)] = s
		nest["/".join(path)] = ns

def getSize(x):
	s = size[x]
	for y in nest[x]:
		s += getSize(f"{x}/{y}")
	return s

req = getSize("/") - 40000000
s = 30000000
for d in size:
	v = getSize(d)
	if v >= req:
		s = min(v, s)
print(s)
