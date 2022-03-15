# February 21st, 2022

file = open("input.txt", "r")

arr = []
for line in file:
	pipes = set()
	for x in line.split(" <-> ")[1].strip().split(", "):
		pipes.add(int(x))
	arr.append(pipes)

checked = set()
count = 0
for i in range(0, len(arr)):
	if i not in checked:
		group = set()
		temp = { i }
		while len(temp):
			copy = set(temp)
			for x in temp:
				group.add(x)
				for y in arr[x]:
					if y not in group:
						copy.add(y)
				copy.remove(x)
			temp = set(copy)
		for x in group:
			checked.add(x)
		count += 1
print(count)
