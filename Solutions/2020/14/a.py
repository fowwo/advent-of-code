# August 6th, 2022

file = open(f"{__file__}/../input.txt", "r")

mem = dict()
m0 = 0
m1 = 0
for line in file:
	s = line.strip().split(" = ")
	if s[0][:4] == "mask":
		m0 = 0
		m1 = 0
		for i in range(36):
			m0 <<= 1
			m1 <<= 1
			if s[1][i] == '0':
				m0 += 0
				m1 += 0
			elif s[1][i] == '1':
				m0 += 1
				m1 += 1
			else:
				m0 += 0
				m1 += 1
	else:
		mem[int(s[0][4:-1])] = (int(s[1]) | m0) & m1

file.close()

s = 0
for x in mem:
	s += mem[x]
print(s)
