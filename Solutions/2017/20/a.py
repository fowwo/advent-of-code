# March 2nd, 2022

file = open("input.txt", 'r')

particles = []
for line in file:
	particle = {}
	split = line.strip().split(", ")
	particle["id"] = len(particles)
	particle["position"] = [ int(x) for x in split[0][3:-1].split(',') ]
	particle["velocity"] = [ int(x) for x in split[1][3:-1].split(',') ]
	particle["acceleration"] = [ int(x) for x in split[2][3:-1].split(',') ]
	particles.append(particle)

m = []
mv = 1000000000
for particle in particles:
	v = sum([ abs(x) for x in particle["acceleration"] ])
	if v == mv:
		m.append(particle)
	elif v < mv:
		m = [ particle ]
		mv = v

# Multiple particles have the same smallest acceleration
mv = 1000000000
for particle in m:
	v = 0
	for i in range(0, 3):
		v += particle["acceleration"][i] * particle["velocity"][i]
	if v == mv:
		m.append(particle)
	elif v < mv:
		m = [ particle ]
		mv = v

print(m[0]["id"])
