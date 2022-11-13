# November 13th, 2022

file = open(f"{__file__}/../input.txt", "r")

vents = [ sorted([ int(x) for x in point.split(",") ] for point in vent.strip().split(" -> ")) for vent in file.readlines() ]

# For now, only consider horizontal and vertical lines
horizontal = sorted([ x for x in vents if x[0][1] == x[1][1] ])
vertical = sorted([ x for x in vents if x[0][0] == x[1][0] ])

intersections = set()

# Perpendicular
for h in horizontal:
	for v in vertical:
		if v[0][0] >= h[0][0] and v[0][0] <= h[1][0] and h[0][1] >= v[0][1] and h[0][1] <= v[1][1]:
			intersections.add((v[0][0], h[0][1]))

# Horizontal parallel
for i in range(len(horizontal) - 1):
	for j in range(i + 1, len(horizontal)):
		if horizontal[i][0][1] == horizontal[j][0][1]:
			a = max(horizontal[i][0][0], horizontal[j][0][0])
			b = min(horizontal[i][1][0], horizontal[j][1][0])
			for k in range(a, b + 1):
				intersections.add((k, horizontal[i][0][1]))

# Vertical parallel
for i in range(len(vertical) - 1):
	for j in range(i + 1, len(vertical)):
		if vertical[i][0][0] == vertical[j][0][0]:
			a = max(vertical[i][0][1], vertical[j][0][1])
			b = min(vertical[i][1][1], vertical[j][1][1])
			for k in range(a, b + 1):
				intersections.add((vertical[i][0][0], k))

print(len(intersections))
