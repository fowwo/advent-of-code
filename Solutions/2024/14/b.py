# December 14th, 2024

import re
from collections import deque

file = open(f"{__file__}/../input.txt", "r")
robots = [ tuple(map(int, re.findall(r"\-?\d+", line.strip()))) for line in file ]
file.close()

w, h = 101, 103
for i in range(1, w * h):
	m = set([ ((x + vx * i) % w, (y + vy * i) % h) for (x, y, vx, vy) in robots ])
	s = set()
	for robot in m:
		if robot in s: continue
		s.add(robot)
		d = deque([ robot ])
		t = 0
		while d:
			(r, c) = d.pop()
			t += 1
			for (x, y) in [ (r - 1, c), (r + 1, c), (r, c - 1), (r, c + 1) ]:
				if (x, y) in m and (x, y) not in s:
					s.add((x, y))
					d.append((x, y))
		if t >= 100: # I'm assuming that the christmas tree will be connected and reasonably large.
			print(i)
			exit()
