# March 10th, 2022

file = open("input/2020/10.txt", "r")
adapters = [int(line.strip()) for line in file]
adapters.append(0)
adapters.sort()

total = 1
count = 1
for i in range(0, len(adapters) - 1):
	if adapters[i+1] - adapters[i] == 3:
		total *= (count ** 2 - 3 * count + 4) / 2
		count = 1
	else:
		count += 1
total *= (count ** 2 - 3 * count + 4) / 2
print(int(total))
