# December 24th, 2023

file = open(f"{__file__}/../input.txt", "r")

def f(x):
	v = 0
	for c in x:
		v += ord(c)
		v *= 17
		v %= 256
	return v

print(sum(f(x) for x in file.readline().strip().split(',')))
