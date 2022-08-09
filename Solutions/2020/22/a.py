# August 8th, 2022

file = open(f"{__file__}/../input.txt", "r")
file.readline()
a = []
line = file.readline().strip()
while line != '':
	a.append(int(line))
	line = file.readline().strip()
file.readline()
b = [ int(line) for line in file ]
file.close()

while len(a) and len(b):
	x = a.pop(0)
	y = b.pop(0)
	if x > y:
		a.append(x)
		a.append(y)
	else:
		b.append(y)
		b.append(x)

s = 0
n = a if len(a) else b
for i in range(len(n)):
	s += n[i] * (len(n) - i)
print(s)
