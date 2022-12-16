# December 15th, 2022 - An extremely slow but correct solution.

file = open(f"{__file__}/../input.txt", "r")

valves = set()
tunnels = dict()
flow = dict()

s = 0
for line in file:
	args = line.strip().split(" ")

	name = args[1]
	f = int(args[4][5:-1])
	to = args[9:]
	for i in range(len(to) - 1):
		to[i] = to[i][:-1]

	valves.add(name)
	tunnels[name] = set(to)
	flow[name] = f

m = [ ("AA", "AA", set(), 0) ]
memo = dict()
rt = 0
for i in range(4, 30):
	temp = []
	for x in m:
		name, elephant, state, pressure = x

		# Open
		if name not in state and flow[name] > 0:
			newState = set(state)
			newPressure = pressure + (30 - i - 1) * flow[name]
			newState.add(name)
			key = (str(sorted([ name, elephant ])), str(sorted(list(newState))))
			if key in memo:
				if newPressure <= memo[key]:
					continue
			memo[key] = newPressure

			temp.append((name, elephant, newState, newPressure))
			rt = max(rt, newPressure)

		# Switch
		for v in tunnels[name]:
			key = (str(sorted([ v, elephant ])), str(sorted(list(state))))
			if key in memo:
				if pressure <= memo[key]:
					continue
			memo[key] = pressure
			temp.append((v, elephant, state, pressure))

	new = []
	for x in temp:
		name, elephant, state, pressure = x

		# Open
		if elephant not in state and flow[elephant] > 0:
			newState = set(state)
			newPressure = pressure + (30 - i - 1) * flow[elephant]
			newState.add(elephant)
			key = (str(sorted([ name, elephant ])), str(sorted(list(newState))))
			if key in memo:
				if newPressure <= memo[key]:
					continue
			memo[key] = newPressure

			new.append((name, elephant, newState, newPressure))
			rt = max(rt, newPressure)

		# Switch
		for v in tunnels[elephant]:
			key = (str(sorted([ name, v ])), str(sorted(list(state))))
			if key in memo:
				if pressure <= memo[key]:
					continue
			memo[key] = pressure
			new.append((name, v, state, pressure))
	m = new
print(rt)
