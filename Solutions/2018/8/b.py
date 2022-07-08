# July 7th, 2022

file = open("input.txt", 'r')
arr = [ int(x) for x in file.read().strip().split(" ") ]
file.close()

def meta(i):
	global arr
	children = arr[i]
	entries = arr[i + 1]
	metadata = []
	offset = 2
	for _ in range(children):
		result = meta(i + offset)
		metadata += [ result[0] ]
		offset += result[1]
	metadata += arr[i + offset:i + entries + offset]
	offset += entries
	return [ metadata, offset ]

def value(meta):
	if isinstance(meta[0], list):
		i = 0
		while isinstance(meta[i], list):
			meta[i] = value(meta[i])
			i += 1
		children = i
		total = 0
		while i < len(meta):
			if meta[i] - 1 < children:
				total += meta[meta[i] - 1]
			i += 1
		return total
	else:
		return sum(meta)

print(value(meta(0)[0]))
