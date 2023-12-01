# November 30th, 2023

file = open(f"{__file__}/../input.txt", "r")

nums = "1 2 3 4 5 6 7 8 9 one two three four five six seven eight nine".split(" ")
vals = [ 1, 2, 3, 4, 5, 6, 7, 8, 9 ] * 2

s = 0
for line in file.readlines():
	p = [ (v, i, line.rfind(n)) for n, v in zip(nums, vals) if (i := line.find(n)) != -1 ]
	mn = min(p, key = lambda x: x[1])[0]
	mx = max(p, key = lambda x: x[2])[0]
	s += 10 * mn + mx
print(s)
