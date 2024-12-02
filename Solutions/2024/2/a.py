# December 1st, 2024

file = open(f"{__file__}/../input.txt", "r")
reports = [ list(map(int, line.strip().split(" "))) for line in file ]
file.close()

def isSafe(r):
	if r != sorted(r) and r != sorted(r, reverse = True): return False
	for i in range(len(r) - 1):
		d = abs(r[i] - r[i + 1])
		if d < 1 or d > 3:
			return False
	return True

print(sum(isSafe(r) for r in reports))
