# Re-import pandas library
import numpy as np
import pandas as pd

# Re-import matplotlib for plotting
import matplotlib.pyplot as plt

# Load the data from the CSV file
file_path = 'data.csv'
data = pd.read_csv(file_path, header=None)  # No header in the file

# Assign appropriate column names
data.columns = ['Size', 'Average Time']

# Create a plot with the x-axis on a log2 scale
plt.figure(figsize=(10, 6))
plt.plot(np.log2(data['Size']), data['Average Time'], marker='o', linestyle='-')

# Set the title and labels of the plot
plt.title('Lab3 Plot from Xiyao Xu')
plt.xlabel('Size (log2 scale)')
plt.ylabel('Average Time (ns)')

# Show grid
plt.grid(True, which="both", ls="--")

#save the figure
plt.savefig('python_example1.png')

# Show the plot
plt.show()
