# March 2nd, 2022

file = open("input/2017/20.txt", 'r')

particles = []
for line in file:
	particle = {}
	split = line.strip().split(", ")
	particle["id"] = len(particles)
	particle["position"] = [ int(x) for x in split[0][3:-1].split(',') ]
	particle["velocity"] = [ int(x) for x in split[1][3:-1].split(',') ]
	particle["acceleration"] = [ int(x) for x in split[2][3:-1].split(',') ]
	particles.append(particle)

# Assuming no collisions after 1000 iterations
for _ in range(0, 1000):
	single = set()
	double = set()
	for particle in particles:
		for i in range(0, 3):
			particle["velocity"][i] += particle["acceleration"][i]
			particle["position"][i] += particle["velocity"][i]
		string = ",".join(str(x) for x in particle["position"])
		if string in single:
			double.add(string)
		else:
			single.add(string)
	copy = [ x for x in particles ]
	for particle in particles:
		if ",".join(str(x) for x in particle["position"]) in double:
			copy.remove(particle)
	particles = copy
print(len(particles))
