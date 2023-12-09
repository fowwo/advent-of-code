# December 8th, 2023

file = open(f"{__file__}/../input.txt", "r")
seqs = [ list(map(int, line.strip().split())) for line in file ]

s = 0
for q in seqs:
	while q:
		s += q[-1]
		q = [ q[i] - q[i - 1] for i in range(1, len(q)) ]
print(s)
