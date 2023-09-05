def replace_separators_extract1(string):
    
    string=string[:210]+'  '+string[231:]
    string=string.replace(',','')
    string=string.replace('"','')
    new_string = 'C-'+string.replace(';', ',', 8)
    new_string = new_string.rsplit(';', 3)
    new_string = ','.join(new_string)
    new_string = new_string.replace('                            1                                  ',',001,',1)
    return new_string.replace('\n',',     \n')
	
def replace_separators_extract2(string):
    new_string = 'C-'+string.replace(';', ',')
    new_string = new_string.replace('                            1                                  ',',001,',1)
    new_string = new_string.replace('                                ','',1)
    return new_string.replace(';',',')

def replace_separators_extract3(string):
    new_string = 'C-'+string.replace(';', ',')
    new_string = new_string.replace('                            1                                  ',',001,',1)
    new_string = new_string.replace('                                ','',1)
    new_string = new_string.replace(';',',')
    indxs = new_string.find('MAD')+3
    return new_string[:indxs]+'\n'

def handle_file(input_file,output_file):
    # Ouvrir le fichier d'entrée en mode lecture
    with open(input_file, 'r') as file:
        lines = file.readlines()
 
    if input_file=="EXTRACT1":
		modified_lines = [[(replace_separators_extract1(line.strip()) + ', \n').replace(' ','').replace(',,',', ,') for line in lines]]
		
    if input_file=="EXTRACT2":
        modified_lines = [replace_separators_extract2(line) for line in lines]
    if input_file=="EXTRACT3":
        modified_lines = [replace_separators_extract3(line) for line in lines]
    # Ouvrir le fichier de sortie en mode écriture
    with open(output_file, 'w') as file:
        file.writelines(modified_lines)
    print(f"La conversion du fichier est terminée, {len(modified_lines)} lignes modifier,  Veuillez consulter le fichier de sortie: {output_file}")
    
# Nom du fichier d'entrée et de sortie
input_file = "EXTRACT1"
output_file = "EXTRACT1.csv"
handle_file(input_file,output_file)

# Nom du fichier d'entrée et de sortie
input_file = "EXTRACT2"
output_file = "EXTRACT2.csv"
handle_file(input_file,output_file)

# Nom du fichier d'entrée et de sortie
input_file = "EXTRACT3"
output_file = "EXTRACT3.csv"
handle_file(input_file,output_file)