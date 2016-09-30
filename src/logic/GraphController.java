package logic;

import java.awt.Color; 
import java.awt.BasicStroke; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

//THIS CLASS WITH HANDLE THE GRAPH

public class GraphController 
{
	
JFreeChart xylineChart;
ChartPanel chartPanel;
XYPlot plot;

//SINGLETON
private static GraphController instance = null;	// DataStorage singleton instance	

public static GraphController getInstance() {
	if(instance == null) {
		instance = new GraphController();
	}
	return instance;
}
	
 public XYSeriesCollection createDataset( )
 {
    final XYSeries localDataset = new XYSeries("Average Stats");          
    localDataset.add( 0 , 0 );          
    
    final XYSeries InputDataset = new XYSeries("Your Stats");          
    InputDataset.add( 0 , 0 );          
    
    XYSeriesCollection dataset = new XYSeriesCollection( );                   
    dataset.addSeries(localDataset);
    dataset.addSeries(InputDataset);
    return dataset;
 }

 public XYSeriesCollection createDataset(double[] localStats, double[] inputStats, int level)
 {      
    final XYSeries InputDataset = new XYSeries("Your Stats");          
    for(int i = 0; i< inputStats.length; i ++)
    {
    	InputDataset.add(i+level,inputStats[i] );          
    }
    
    final XYSeries localDataset = new XYSeries("Average Stats");  
    for(int i = 0; i< localStats.length; i ++)
    {
    localDataset.add(i+level, localStats[i]);          
    }
    
    XYSeriesCollection dataset = new XYSeriesCollection( );                   
    dataset.addSeries(localDataset);
    dataset.addSeries(InputDataset);

    return dataset;
 }
 
 public void createGraph( String applicationTitle)
 {
    xylineChart = ChartFactory.createXYLineChart(
    	applicationTitle ,
       "" ,
       "Stat" ,
       createDataset( ),
       PlotOrientation.VERTICAL ,
       true , false , false);
       
    chartPanel = new ChartPanel( xylineChart );
    chartPanel.setPreferredSize( new java.awt.Dimension( 400 , 200) );
    plot = xylineChart.getXYPlot( );
    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
    renderer.setSeriesPaint( 0 , Color.RED );
    renderer.setSeriesPaint( 1 , Color.GREEN );
    renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
    renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
    plot.setRenderer( renderer ); 
}
 
 public void setDataset(XYSeriesCollection dataset)
 {
	 plot.setDataset(dataset);
 }

 public ChartPanel getChartPanel()
 {
	 return chartPanel;
 }
 
 public JFreeChart getChart()
 {
	 return xylineChart;
 }
}
