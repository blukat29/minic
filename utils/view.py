import xml.etree.ElementTree as ET
import re

tree = ET.parse("../ast.xml")
node = tree.getroot().find('parsetree')

root = node.find('nonterminal')

posre = re.compile(r'(\d+)/(\d+)')

def traverse(node, indent=0):
    name = node.get('id', '??')
    text = node.text if node.text else ''
    m = posre.findall(node.get('left', ''))
    pos = '(%s:%s)' % m[0] if m else ''
    t = '####' if node.tag == 'terminal' else ''
    print '   .'*(indent-1) + '   ' + name, pos, text, t

    # Childeren
    for child in node.getchildren():
        if child.tag in ['terminal', 'nonterminal']:
            traverse(child, indent+1)

traverse(root)

