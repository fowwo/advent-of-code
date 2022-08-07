# August 6th, 2022

file = open(f"{__file__}/../input.txt", "r")
file.readline()
buses = file.readline().strip().split(',')
indices = [ buses.index(x) for x in buses if x != 'x' ]
buses = [ int(x) for x in buses if x != 'x' ]
file.close()

t = 0
inc = 1
for i in range(len(buses)):
	b = buses[i]
	n = indices[i]
	while (t + n) % b:
		t += inc
	inc *= b
print(t)
