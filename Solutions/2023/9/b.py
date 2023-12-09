# December 8th, 2023

file = open(f"{__file__}/../input.txt", "r")
seqs = [ list(map(int, line.strip().split())) for line in file ]

s = 0
for q in seqs:
	m = []
	while q:
		m.append(q)
		q = [ q[i] - q[i - 1] for i in range(1, len(q)) ]
	t = 0
	for q in m[::-1]:
		t = q[0] - t
	s += t
print(s)
