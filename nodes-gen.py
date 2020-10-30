import itertools
import random
import datetime

# get datetime
timeId = datetime.datetime.now().strftime("%Y%m%d%H%M%S")

# define program parameters
filename = "./nodes-%s.txt" % timeId
nodeCount = 1000
maxCost = 20

# seed random with datetime
random.seed(timeId)

# open file for writing
print("Starting...")
with open(filename, "w") as f:
	
	# write number of nodes to file
	f.write(str(nodeCount))

	# get amount of pairs we want and determine probability of success
	approxMaxPairs = nodeCount * 9
	probability = approxMaxPairs / sum(range(0, nodeCount))

	# vars to be updated in loop
	pairs = []
	nodeRequired = 0

	# iterate through all possible combinations of edges
	for result in itertools.combinations(range(0, nodeCount), 2):
		
		# make artificial cost and get random
		cost = random.randint(0, maxCost)
		randomFloat = random.random()

		# check if we need this node
		if result[0] == nodeRequired:
			nodeRequired += 1
			randomFloat = 0

		# add node if we need it or by chance
		if randomFloat < probability:
			pairs.append("%s %s %s" % (result[0], result[1], cost))

	# write pairs to file
	for pair in pairs:
		f.write("\n" + pair)

# state completion
print("Done")
