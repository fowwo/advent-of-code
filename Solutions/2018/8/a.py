# July 7th, 2022

file = open("input.txt", 'r')
arr = [ int(x) for x in file.read().strip().split(" ") ]
file.close()

def meta(i):
	global arr
	children = arr[i]
	entries = arr[i + 1]
	total = 0
	offset = 2
	for _ in range(children):
		result = meta(i + offset)
		total += result[0]
		offset += result[1]
	total += sum(arr[i + offset:i + entries + offset])
	offset += entries
	return [ total, offset ]

print(meta(0)[0])
