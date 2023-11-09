import csv

with open('contains_experiment.tsv', 'r') as tsvfile:
    reader = csv.reader(tsvfile, delimiter='\t')
    with open('data.csv', 'w') as csvfile:
        writer = csv.writer(csvfile)
        for row in reader:
            writer.writerow(row)
