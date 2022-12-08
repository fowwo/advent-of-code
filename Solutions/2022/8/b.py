# December 7th, 2022

file = open(f"{__file__}/../input.txt", "r")

trees = [ [ int(x) for x in line.strip() ] for line in file ]

mx = 0
for i in range(1, len(trees) - 1):
	for j in range(1, len(trees) - 1):
		tree = trees[i][j]
		l = 1
		r = 1
		u = 1
		d = 1
		while j - l > 0 and trees[i][j - l] < tree:
			l += 1
		while j + r < len(trees) - 1 and trees[i][j + r] < tree:
			r += 1
		while i - u > 0 and trees[i - u][j] < tree:
			u += 1
		while i + d < len(trees) - 1 and trees[i + d][j] < tree:
			d += 1
		score = l * r * u * d
		mx = max(score, mx)
print(mx)
