# December 24th, 2022

file = open(f"{__file__}/../input.txt", "r")

s = 0
for line in file:
	v = 0
	for c in line.strip():
		v *= 5
		v += "=-012".find(c) - 2
	s += v

snafu = ""
while s > 0:
	q = (s + 2) % 5 - 2
	snafu += "=-012"[q + 2]
	s = (s - q) // 5
print(snafu[::-1])
