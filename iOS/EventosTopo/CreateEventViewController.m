//
//  CreateEventViewController.m
//  EventosTopo
//
//  Created by Fede Cugliandolo on 19/05/14.
//  Copyright (c) 2014 Fede. All rights reserved.
//

#import "CreateEventViewController.h"

@interface CreateEventViewController ()
@property (nonatomic, strong) NSMutableArray *events;
@end

@implementation CreateEventViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    NSUserDefaults *defatuls = [NSUserDefaults standardUserDefaults];
    self.events = [[NSMutableArray alloc] initWithArray:[defatuls objectForKey:EVENT_KEY]];
}

- (IBAction)saveEventTapped:(id)sender {
    [self.events addObject:[NSString stringWithFormat:@"evento %d", self.events.count +1]];
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    [defaults setObject:self.events forKey:EVENT_KEY];
    [defaults synchronize];
    
    [self.navigationController popViewControllerAnimated:YES];
}

@end
