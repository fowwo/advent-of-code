# February 17th, 2022

file = open("input/2017/11.txt", "r")
moves = file.readline().strip().split(",")

count = {
	"nw": 0,
	"n": 0,
	"ne": 0,
	"sw": 0,
	"s": 0,
	"se": 0
}

maxSteps = 0
for move in moves:
	count[move] += 1

	# Shorten diagonal-diagonal pairs
	for pair in [["ne", "nw"], ["se", "sw"]]:
		m = min(count[pair[0]], count[pair[1]])
		count[pair[0]] -= m
		count[pair[1]] -= m
		count[pair[0][0]] += m

	# Remove backtracking moves
	for pair in [["nw", "se"], ["n", "s"], ["ne", "sw"]]:
		m = min(count[pair[0]], count[pair[1]])
		count[pair[0]] -= m
		count[pair[1]] -= m

	# Shorten diagonal-vertical pairs
	for pair in [["ne", "s"], ["nw", "s"], ["se", "n"], ["sw", "n"]]:
		m = min(count[pair[0]], count[pair[1]])
		count[pair[0]] -= m
		count[pair[1]] -= m
		count[pair[1] + pair[0][1]] += m

	maxSteps = max(maxSteps, sum(count.values()))

print(maxSteps)
