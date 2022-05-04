# May 4th, 2022

file = open("input.txt", 'r')
lines = file.read().splitlines()
file.close()

n = 119315717514047
m = 101741582076661
head = 0
gap = 1

for line in lines:
	if line.startswith("cut"):
		value = int(line.split(" ")[-1])
		head += value * gap
	elif line.startswith("deal with"):
		value = int(line.split(" ")[-1])
		gap *= pow(value, -1, n)
	else:
		gap *= -1
		head += gap

# Find final head position - sum of geometric series mod n
degree = m
x = gap % n
y = 1
total = 0
while degree > 0:
	if degree & 1:
		total *= x
		total += y
	y = ((x + 1) * y) % n
	x = (x * x) % n 
	degree //= 2
head *= total
head %= n

# Find final gap
gap = pow(gap, m, n)

# Find card in 2020th position
print((head + 2020 * gap) % n)
