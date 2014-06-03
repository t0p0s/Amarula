//
//  EventsViewController.m
//  EventosTopo
//
//  Created by Fede Cugliandolo on 19/05/14.
//  Copyright (c) 2014 Fede. All rights reserved.
//

#import "EventsViewController.h"

@interface EventsViewController ()
@property (strong, nonatomic) IBOutlet UITableView *eventsTable;
@property (strong, nonatomic) NSMutableArray *events;

@end

@implementation EventsViewController

-(void)viewWillAppear:(BOOL)animated
{
    NSUserDefaults *defatuls = [NSUserDefaults standardUserDefaults];
    self.events = [[NSMutableArray alloc] initWithArray:[defatuls objectForKey:EVENT_KEY]];
    [self.eventsTable reloadData];
}

- (void)viewDidLoad
{
    [super viewDidLoad];

    self.events = [[NSMutableArray alloc] initWithCapacity:10];
}

#pragma mark - Table View Delegate & Data Source
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return self.events.count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"EventCellID" forIndexPath:indexPath];
    if (!cell) cell = [tableView dequeueReusableCellWithIdentifier:@"EventCellID"];
    cell.textLabel.text = [NSString stringWithFormat:@"Event %d", indexPath.row +1];
    
    return cell;
}

@end
