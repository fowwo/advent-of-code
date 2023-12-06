# December 5th, 2023

file = open(f"{__file__}/../input.txt", "r")
time = map(int, file.readline().split(":")[1].strip().split())
distance = map(int, file.readline().split(":")[1].strip().split())

p = 1
for t, d in zip(time, distance):
	i = 1
	while i * (t - i) <= d:
		i += 1
	p *= t + 1 - 2 * i
print(p)
