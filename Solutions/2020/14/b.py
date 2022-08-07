# August 6th, 2021

file = open(f"{__file__}/../input.txt", "r")

mem = dict()
mask = None
m1 = 0
for line in file:
	s = line.strip().split(" = ")
	if s[0][:4] == "mask":
		mask = s[1]
		m1 = 0
		for i in range(36):
			m1 <<= 1
			if s[1][i] == '1':
				m1 += 1
	else:
		indices = [ 35 - i for i in range(len(mask)) if mask[i] == 'X' ]
		binary = [[0],[1]]
		for _ in range(len(indices) - 1):
			new = []
			for a in binary:
				new.append(a + [0])
				new.append(a + [1])
			binary = new
		for b in binary:
			x = int(s[0][4:-1])
			for i in range(len(b)):
				x &= ~(1 << indices[i])
				x |= b[i] << indices[i]
			x |= m1
			mem[x] = int(s[1])

file.close()

s = 0
for value in mem:
	s += mem[value]
print(s)
