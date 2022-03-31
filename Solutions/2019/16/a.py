# March 31st, 2022

file = open("input.txt", 'r')

array = [ int(x) for x in file.readline().strip() ]
file.close()

for n in range(0, 100):
	new = []
	for a in range(0, len(array)):
		s = 0
		for b in range(0, len(array)):
			s += array[b] * (1 - abs((b + 1) // (a + 1) % 4 - 1))
		new.append(abs(s) % 10)
	array = new
print("".join([str(x) for x in array[0:8]]))
