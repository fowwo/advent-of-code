# December 17th, 2024

file = open(f"{__file__}/../input.txt", "r")
bytes = [ tuple(map(int, line.strip().split(','))) for line in file ]
file.close()

def f(i):
	m = [ (0, 0) ]
	s = set(bytes[:i] + m)
	while m:
		n = []
		for (r, c) in m:
			for (x, y) in [ (r + 1, c), (r, c + 1), (r - 1, c), (r, c - 1) ]:
				if 0 <= x <= 70 and 0 <= y <= 70 and (x, y) not in s:
					if (x, y) == (70, 70):
						return True
					s.add((x, y))
					n.append((x, y))
			m = n
	return False

i = 1025
while f(i): i += 1
print(','.join(str(x) for x in bytes[i - 1]))
