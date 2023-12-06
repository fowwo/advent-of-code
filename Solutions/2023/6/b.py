# December 5th, 2023

file = open(f"{__file__}/../input.txt", "r")
t = int("".join(file.readline().split(":")[1].strip().split()))
d = int("".join(file.readline().split(":")[1].strip().split()))

i = 1
while i * (t - i) <= d:
	i += 1
print(t + 1 - 2 * i)
