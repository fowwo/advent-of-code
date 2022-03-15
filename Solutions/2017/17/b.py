# February 22nd, 2022

step = 335

i = 0
zero = 0
after = 1
for x in range(1, 50000001):
	i = (i + step) % x + 1
	if i < zero:
		zero += 1
	elif i == zero + 1:
		after = x
print(after)
