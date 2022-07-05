import numpy as np
import math
import pygame
import matplotlib.pyplot as plt
import pandas as pd
from shapely.ops import cascaded_union

MAX = 147
SCALE = 10
# reclat: the latitude of the meteorite's landing
# reclong: the longitude of the meteoite's landing

def main():
    # create the screen
    screen = pygame.display.set_mode((1300, 600))
    screen.fill((255, 255, 255))

    x, y = populate_coords()
    create_map(screen, x, y)
    pygame.display.flip()

    # Game Loop
    running = True
    while running:
        screen.fill((255, 255, 255))
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

def populate_coords():
    df = pd.read_csv('meteorite-landings.csv')
    geolocation = (df['GeoLocation'])
    x = []
    y = []  
    for i in range(MAX):
        geolocationSplit = (geolocation[i].split(','))
        x.append((float)(geolocationSplit[1].strip(')')) * SCALE)
        y.append((float)(geolocationSplit[0].strip('(')) * SCALE)

    return x, y

def create_map(screen, x, y):
    for i in range(MAX):
        pygame.draw.circle(screen, (0, 255, 0),[x[i], y[i]], 2, 0)

main()