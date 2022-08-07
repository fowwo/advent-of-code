# August 6th, 2022

file = open(f"{__file__}/../input.txt", "r")
start = int(file.readline().strip())
buses = [ int(x) for x in file.readline().strip().split(',') if x != 'x' ]
file.close()

time = start
while True:
	for bus in buses:
		if time % bus == 0:
			print((time - start) * bus)
			exit()
	time += 1
