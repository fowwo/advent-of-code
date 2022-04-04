# April 4th, 2022

file = open("input.txt", 'r')

array = [ int(x) for x in file.readline().strip() ]
file.close()

skip = 0
for i in range(0, 7):
	skip *= 10
	skip += array[i]

length = 10000 * len(array) - skip
copy = [x for x in array]
while len(array) < length:
	for x in copy:
		array.append(x)
while len(array) > length:
	del array[0]

for n in range(0, 100):
	for i in range(1, length):
		array[length-i-1] += array[length-i]
		array[length-i-1] %= 10
print("".join([ str(x) for x in array[0:8] ]))
