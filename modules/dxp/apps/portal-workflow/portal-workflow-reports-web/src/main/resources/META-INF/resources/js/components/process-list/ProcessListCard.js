import autobind from 'autobind-decorator';
import EmptyContent from '../../shared/components/EmptyContent';
import gql from 'graphql-tag';
import ProcessListEntries from './ProcessListEntries';
import ProcessListPagination from './ProcessListPagination';
import ProcessListPaginationResults from './ProcessListPaginationResults';
import ProcessListSearch from './ProcessListSearch';
import ProcessListTable from './ProcessListTable';
import React from 'react';

export default class ProcessListCard extends React.Component {
	constructor() {
		super();

		this.state = {
			noDataResult: false,
			noSearchResult: false,
			processes: [],
			selectedEntry: 20,
			start: 0,
			total: 0
		};
	}

	componentWillMount() {
		const {selectedEntry, start} = this.state;
		const {client} = this.props;

		const unsubscribe = client.onResetStore(() => {
			this.requestData({size: selectedEntry, start}).then(
				({processes, total}) =>
					this.setState({
						noDataResult: total === 0,
						processes,
						total
					})
			);

			unsubscribe();
		});

		client.resetStore();
	}

	requestData({keyword, size, start}) {
		const {client, companyId} = this.props;

		return client
			.query({
				query: gql`
				query workflowProcessesQuery {
					processes(companyId:${companyId}, keyword:"${keyword}", start:${start}, size:${size}) {
						total
						workflowProcesses {
	  						title
	  						instancesCount
						}
					}
				}
			`
			})
			.then(({data: {processes}}) => ({
				processes: processes.workflowProcesses,
				total: processes.total
			}))
			.catch(error => console.error(error));
	}

	@autobind
	onSearch(keyword) {
		const {selectedEntry} = this.state;
		const start = 0;

		this.requestData({keyword, size: selectedEntry, start}).then(
			({processes, total}) =>
				this.setState({noSearchResult: total === 0, processes, start, total})
		);
	}

	@autobind
	setPage({size, start}) {
		return this.requestData({size, start}).then(({processes, total}) =>
			this.setState({processes, start, total})
		);
	}

	@autobind
	setEntry(entry) {
		const start = 0;

		this.requestData({size: entry, start}).then(({processes, total}) =>
			this.setState({processes, selectedEntry: entry, start, total})
		);
	}

	render() {
		const {
			noDataResult,
			noSearchResult,
			processes,
			selectedEntry,
			start,
			total
		} = this.state;
		const entries = [5, 10, 20, 30, 50, 75];

		const paginationBar = () => {
			if (total > entries[0]) {
				return (
					<div className="pagination-bar">
						<ProcessListEntries
							entries={entries}
							onSelectEntry={this.setEntry}
							selectedEntry={selectedEntry}
						/>

						<ProcessListPaginationResults
							count={processes.length}
							start={start}
							total={total}
						/>

						<ProcessListPagination
							entry={selectedEntry}
							pageClickHandler={this.setPage}
							start={start}
							totalCount={total}
						/>
					</div>
				);
			}
			return <div />;
		};

		return (
			<div>
				<ProcessListSearch disabled onSearch={this.onSearch} />
				<div className="container-fluid-1280">
					{noSearchResult ? (
						<EmptyContent
							message={Liferay.Language.get('no-results-were-found')}
							type="not-found"
						/>
					) : noDataResult && total === 0 ? (
						<EmptyContent
							message={Liferay.Language.get(
								'once-there-are-active-processes-reports-will-appear-here'
							)}
							title={Liferay.Language.get('no-current-reports')}
						/>
					) : total > 0 ? (
						<div>
							<ProcessListTable processes={processes} />
							{paginationBar()}
						</div>
					) : (
						<span aria-hidden="true" className="loading-animation" />
					)}
				</div>
			</div>
		);
	}
}